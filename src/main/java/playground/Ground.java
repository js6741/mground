package playground;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Entity
@Table(name="Ground_table")
public class Ground {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String groundName;
    private String groundType;
    private Integer maxPlayer;

    @PostPersist
    public void onPostPersist(){
        GroudRegistered groudRegistered = new GroudRegistered();
        BeanUtils.copyProperties(this, groudRegistered);
        groudRegistered.publishAfterCommit();


    }

    /*@PreRemove
    public void onPreRemove(){*/
    @PostUpdate
    public void onPostUpdate(){
        GroundDeleted groundDeleted = new GroundDeleted();
        BeanUtils.copyProperties(this, groundDeleted);
        groundDeleted.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        playground.external.Schedule schedule = new playground.external.Schedule();
        // mappings goes here

        schedule.setGroundId(this.getId());
        /*schedule.setGroundType(this.getGroundType());
        schedule.setMaxPlayer(this.getMaxPlayer());
        schedule.setDate("20200801");*/
        schedule.setStatus("Deleted");

        GroundApplication.applicationContext.getBean(playground.external.ScheduleService.class)
                .scheduleDelete(schedule);

        /*Schedule schedule = new ScheduleCreated();
        BeanUtils.copyProperties(this, scheduleCreated);
        scheduleCreated.publishAfterCommit();*/

        //결재 요청 후 kafka
        /*Payment payment = new Payment();
        payment.setOrderId(getId());
        payment.setRentalPrice(getRentalPrice());
        payment.setStatus("승인요청");
        payment.setApprovalDate(getContractDate());
        OrderApplication.applicationContext.getBean(PaymentService.class)
                .approval(payment);

        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();*/
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }
    public String getGroundType() {
        return groundType;
    }

    public void setGroundType(String groundType) {
        this.groundType = groundType;
    }
    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(Integer maxPlayer) {
        this.maxPlayer = maxPlayer;
    }




}
