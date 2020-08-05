package playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 public class GroundController {

  @Autowired
  GroundRepository groundRepository;

  @RequestMapping(method=RequestMethod.PATCH, path="/grounds/delete")
  public void groundDelete(@RequestParam(value="groundId", required=false) String groundId) {

/*   Optional<Ground> ground = groundRepository.findById(Long.valueOf(groundId));
   //books.get().setId(Long.valueOf(bookId));
   ground.setStatus("Deleted");
   bookRepository.save(book);*/
  }

 }
