package esp.quickstart

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class HealthController {

    val counter = AtomicLong()

    @GetMapping("/check")
    fun check(@RequestParam("name", defaultValue = "World") name: String) =
        Health(counter.incrementAndGet(), "Hello, $name")
}