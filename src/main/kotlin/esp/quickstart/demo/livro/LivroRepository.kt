package esp.quickstart.demo.livro

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LivroRepository : JpaRepository<Livro, Long>