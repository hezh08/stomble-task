package stomble.task;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
    
}
