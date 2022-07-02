package pro.azhidkov.sdjdicts

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("map_point_car_types")
data class CarType(
    @Id
    val id: Long,
    val name: String,
    val description: String
)