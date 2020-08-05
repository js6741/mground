
package playground.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name="schedule", url="http://localhost:8082")
//@FeignClient(name="schedule", url="http://schedule:8080")
public interface ScheduleService {

    @RequestMapping(method=RequestMethod.POST, path="/schedules/delete")
    public void scheduleDelete(@RequestBody Schedule schedule);
    //public void scheduleDelete(@RequestParam(value="groundId", required=false) String groundId);
    //public void scheduleCreate(@RequestBody Schedule schedule);

}