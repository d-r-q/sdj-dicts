package pro.azhidkov.sdjdicts

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant


@Table("map_points")
data class MapPoint(
    val approved: Boolean,
    val createdAt: Instant,
    @MappedCollection(idColumn = "map_point_id")
    val carTypes: Set<CarType>,
    @Id
    val id: Long = 0,
)