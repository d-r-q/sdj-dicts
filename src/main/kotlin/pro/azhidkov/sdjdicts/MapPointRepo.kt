package pro.azhidkov.sdjdicts

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface MapPointRepo : CrudRepository<MapPoint, Long> {

    @Query("SELECT * FROM car_types")
    fun carTypes(): List<CarType>

}