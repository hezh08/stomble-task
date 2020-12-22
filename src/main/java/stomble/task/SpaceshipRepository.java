package stomble.task;

import org.springframework.data.jpa.repository.JpaRepository;

// Memory-based database

interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
    
}
