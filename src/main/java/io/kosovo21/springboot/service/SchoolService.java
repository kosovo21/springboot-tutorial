package io.kosovo21.springboot.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.kosovo21.springboot.dao.ClassDao;
import io.kosovo21.springboot.dao.StudentDao;
import io.kosovo21.springboot.dto.StudentDto;
import io.kosovo21.springboot.entity.ClassEntity;
import io.kosovo21.springboot.entity.StudentEntity;
import io.kosovo21.springboot.util.SearchCriteria;

@Service
public class SchoolService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ClassDao classDao;

	@Autowired
	private EntityManager entityManager;

	public StudentEntity save(StudentEntity student) {
		return studentDao.save(student);
	}

	public ClassEntity save(ClassEntity clasz) {
		return classDao.save(clasz);
	}

	public Optional<StudentEntity> findById(Long id) {
		return studentDao.findById(id);
	}

	public Page<StudentDto> search(List<SearchCriteria> params, int page, int size) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<StudentDto> query = builder.createQuery(StudentDto.class);

		final Root<StudentEntity> r = query.from(StudentEntity.class);
		Join<StudentEntity, ClassEntity> c = r.join("currentClass", JoinType.LEFT);

		Predicate predicate = builder.conjunction();
		for (SearchCriteria param : params) {
			predicate = appendQuery(predicate, builder, param, r, false);

			String relation = param.getKey();
			if (relation.contains("currentClass")) {
				predicate = appendQuery(predicate, builder, param, c, true);
			}
		}

		query.where(predicate);
		query.select(builder.construct(StudentDto.class, r.get("id"), r.get("name"), c.get("grade"), c.get("major")));

		TypedQuery<StudentDto> typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult(page * size);
		if (size > 0) {
			typedQuery.setMaxResults(size);
		}
		List<StudentDto> studentDtos = typedQuery.getResultList();
		long countFiltered = countFiltered(predicate);
		return new PageImpl<>(studentDtos, PageRequest.of(page, size), countFiltered);
	}

	private long countFiltered(Predicate predicate) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> queryCount = builder.createQuery(Long.class);

		final Root<StudentEntity> r = queryCount.from(StudentEntity.class);
		r.join("currentClass", JoinType.LEFT);

		queryCount.where(predicate);
		queryCount.select(builder.count(r));
		return entityManager.createQuery(queryCount).getSingleResult();
	}

	private <A, B> Predicate appendQuery(Predicate predicate, CriteriaBuilder builder, SearchCriteria param,
			From<A, B> r, boolean isRelation) {

		String key = param.getKey();
		if (param.getKey().contains("_")) {
			key = param.getKey().split("_")[1];
			if (!isRelation) {
				return predicate;
			}
		}

		if (param.getKey().contains("Date")) {
			if (param.getOperation().equalsIgnoreCase(">")) {
				predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(key),
						new Timestamp(Long.parseLong(param.getValue().toString()))));
			} else if (param.getOperation().equalsIgnoreCase("<")) {
				predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(key),
						new Timestamp(Long.parseLong(param.getValue().toString()))));
			} else if (param.getOperation().equalsIgnoreCase(":")) {
				predicate = builder.and(predicate,
						builder.equal(r.get(key), new Timestamp(Long.parseLong(param.getValue().toString()))));
			} else if (param.getOperation().equalsIgnoreCase("!")) {
				predicate = builder.and(predicate,
						builder.notEqual(r.get(key), new Timestamp(Long.parseLong(param.getValue().toString()))));
			}
		} else {
			if (param.getOperation().equalsIgnoreCase(">")) {
				predicate = builder.and(predicate, builder.greaterThanOrEqualTo(builder.upper(r.get(key)),
						param.getValue().toString().toUpperCase()));
			} else if (param.getOperation().equalsIgnoreCase("<")) {
				predicate = builder.and(predicate, builder.lessThanOrEqualTo(builder.upper(r.get(key)),
						param.getValue().toString().toUpperCase()));
			} else if (param.getOperation().equalsIgnoreCase(":")) {
				predicate = builder.and(predicate,
						builder.equal(builder.upper(r.get(key)), param.getValue().toString().toUpperCase()));
			} else if (param.getOperation().equalsIgnoreCase("~")) {
				predicate = builder.and(predicate,
						builder.like(builder.upper(r.get(key)), "%" + param.getValue().toString().toUpperCase() + "%"));
			} else if (param.getOperation().equalsIgnoreCase("!")) {
				predicate = builder.and(predicate,
						builder.notEqual(builder.upper(r.get(key)), param.getValue().toString().toUpperCase()));
			}
		}

		return predicate;
	}

}
