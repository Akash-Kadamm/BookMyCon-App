package com.dbMigration.demo.kafka.consumer;

import com.dbMigration.demo.payload.Department;
import com.dbMigration.demo.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DepartmentConsumer {

    private static final Logger logger = Logger.getLogger(DepartmentConsumer.class);

    @Autowired
    private DepartmentService departmentService;


    /*
     * Creating Kafka Listener which listen Department record.
     *
     * @param Department
     *
     * */
    @KafkaListener(topics = "${spring.kafka.department-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRecord(Department department) {
        logger.info("Consuming Department record..:" + department.getDepartmentName());
        departmentService.saveDepartment(department);
    }

}
