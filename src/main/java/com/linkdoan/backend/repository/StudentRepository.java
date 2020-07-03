package com.linkdoan.backend.repository;

import com.linkdoan.backend.base.dto.SearchDTO;
import com.linkdoan.backend.dto.StudentsDTO;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.StudentsModel;
import com.linkdoan.backend.model.User;
import com.linkdoan.backend.repository.common.HibernateRepository;
import com.linkdoan.backend.util.ValidateUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import java.math.BigInteger;
import java.util.List;

@Repository
public class StudentRepository extends HibernateRepository<User,Long> {
    @SuppressWarnings("unchecked")
    public List<StudentsDTO> doSearch(SearchDTO obj){
        StringBuilder sql = new StringBuilder("SELECT * from student"
                +"WHERE 1=1 ");

        NativeQuery query= currentSession().createNativeQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(UserDTO.class));

        query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());

        return query.list();
    }
}