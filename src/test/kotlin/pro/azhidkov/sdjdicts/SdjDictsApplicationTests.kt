package pro.azhidkov.sdjdicts

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.containers.PostgreSQLContainer
import java.time.Instant


@SpringBootTest
@Sql("/sql/demo_data.sql")
class SdjDictsApplicationTests(
    @Autowired val repo: MapPointRepo
) {

    companion object {

        private val cont = PostgreSQLContainer("postgres:14.2").apply {
            withExposedPorts(5432)
            withUsername("sdj-dicts")
            withPassword("password")
            withDatabaseName("sdj-dicts")
            withTmpFs(mapOf("/var" to "rw"))
            withEnv("PGDATA", "/var/lib/postgresql/data-no-mounted")
            withReuse(true)
            start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun datasourceConfig(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { cont.jdbcUrl }
            registry.add("spring.datasource.username") { cont.username }
            registry.add("spring.datasource.password") { cont.password }
        }
    }

    @Test
    fun contextLoads() {
        val mapPoints = repo.findAll()
        println(mapPoints)

        val carTypes = repo.carTypes()
        println(carTypes)

        val newMp = MapPoint("map_point3", Instant.now(), carTypes.toSet())
        val saved = repo.save(newMp)
        println(repo.findById(saved.id))
        println(repo.carTypes().size)
    }

    @Test
    fun addByRef() {
        val mapPoint = repo.findAll().first()
        val updated = mapPoint.addCarType(CarType.ref(5))
        val id = repo.save(updated).id
        println(repo.findById(id))
    }

}
