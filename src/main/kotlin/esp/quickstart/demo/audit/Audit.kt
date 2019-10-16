package esp.quickstart.demo.audit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = arrayOf("createdAt", "updatedAt", "version"), allowGetters = true)
open class Audit()
{

    @Version
    var version: Long? = null

    //@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    var createdAt: Timestamp? = null

    //@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    var updatedAt: Timestamp? = null
}