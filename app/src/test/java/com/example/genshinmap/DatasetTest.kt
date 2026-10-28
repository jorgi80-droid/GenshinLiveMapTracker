package com.example.genshinmap
import com.example.genshinmap.core.dataset.Dataset
import org.junit.Assert.*
import org.junit.Test
class DatasetTest {
 @Test fun parsesMarkers(){val parsed=Dataset.parse("""{"schemaVersion":1,"markers":[{"id":"x","name":"A","type":"OCULUS","region":"Mondstadt","x":0.2,"y":0.3}]}""");assertEquals(1,parsed.size);assertEquals("x",parsed.first().id)}
 @Test(expected=IllegalArgumentException::class) fun rejectsBadCoordinates(){Dataset.parse("""{"schemaVersion":1,"markers":[{"id":"x","x":2,"y":0.3}]}""")}
}