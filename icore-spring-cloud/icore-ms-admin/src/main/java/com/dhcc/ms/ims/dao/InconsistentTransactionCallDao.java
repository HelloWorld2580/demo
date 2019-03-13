package com.dhcc.ms.ims.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.po.Transaction;
import com.github.abel533.mapper.Mapper;

public interface InconsistentTransactionCallDao extends Mapper<InconsistentTransaction> {

    @Delete("DELETE FROM msims_inconsistent_transaction WHERE inconsistent_transaction_id=#{id}")
    void deleteInconsistentTransaction(InconsistentTransaction inconsistentTransaction);

    @Delete("DELETE FROM msims_inconsistent_transaction WHERE inconsistent_transaction_id=#{id}")
    void deleteInconsistentTransactionById(String id);

    @Insert("INSERT INTO msims_inconsistent_transaction(inconsistent_transaction_id, inconsistent_transaction_create_time, transaction_id, create_time, service_name, target_class, method_name, args, args_view) "
            + "VALUES (#{inconsistentTransaction.id}, #{inconsistentTransaction.createTime}, #{transaction.id}, #{transaction.createTime}, #{transaction.serviceName}, #{transaction.targetClass}, #{transaction.methodName}, #{transaction.args,typeHandler=com.dhcc.ms.ims.dao.handler.ObjectArraySerializatioTypeHandler}, #{transaction.args,typeHandler=com.dhcc.ms.ims.dao.handler.ObjectArrayViewTypeHandler})")
    void insertInconsistentTransaction(
            @Param("inconsistentTransaction") InconsistentTransaction inconsistentTransaction,
            @Param("transaction") Transaction transaction);

    @Select("SELECT COUNT(DISTINCT inconsistent_transaction_id) FROM msims_inconsistent_transaction "
            + "WHERE inconsistent_transaction_create_time >= #{startTimestamp} AND inconsistent_transaction_create_time < #{endTimestamp}")
    int selectInconsistentTransactionCount(@Param("startTimestamp") Date startTimestamp,
            @Param("endTimestamp") Date endTimestamp);

    @Select("SELECT DISTINCT inconsistent_transaction_id,inconsistent_transaction_create_time FROM msims_inconsistent_transaction "
            + "ORDER BY inconsistent_transaction_create_time")
    @Results(@Result(column = "inconsistent_transaction_id"))
    List<String> selectAllInconsistentTransactionIds();

    @Select("SELECT DISTINCT inconsistent_transaction_id,inconsistent_transaction_create_time FROM msims_inconsistent_transaction "
            + "WHERE create_time LIKE CONCAT('%',#{filter},'%') " + "OR service_name LIKE CONCAT('%',#{filter},'%') "
            + "OR target_class LIKE CONCAT('%',#{filter},'%') " + "OR method_name LIKE CONCAT('%',#{filter},'%') "
            + "ORDER BY inconsistent_transaction_create_time")
    @Results(@Result(column = "inconsistent_transaction_id"))
    List<String> filterInconsistentTransactionIds(String filter);

    List<InconsistentTransaction> selectInconsistentTransactions(List<String> inconsistentTransactionIds);

    InconsistentTransaction selectInconsistentTransaction(String inconsistentTransactionId);

}
