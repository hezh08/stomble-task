package stomble.task;

import org.springframework.data.jpa.repository.CrudRepository;

interface SpaceshipRepository extends CrudRepository<Spaceship, Long> {
    
}
