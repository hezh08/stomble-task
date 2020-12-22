package stomble.task;

import org.springframework.data.jpa.repository.JpaRepository;

//
// Memory-based database
//

interface LocationRepository extends JpaRepository<Location, Long> {

}