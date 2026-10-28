package com.example.genshinmap
import com.example.genshinmap.core.database.CollectionStateEntity
import com.example.genshinmap.core.database.MarkerEntity
import com.example.genshinmap.core.domain.Domain
import org.junit.Assert.*
import org.junit.Test
class DomainTest {
 @Test fun progressCountsKnownCollectedMarkers(){val markers=listOf(MarkerEntity("a","A","OCULUS","Mondstadt",.2,.2),MarkerEntity("b","B","RESOURCE","Liyue",.3,.3));assertEquals(.5f,Domain.progress(markers,listOf(CollectionStateEntity("a","COLLECTED"))),.001f)}
 @Test fun filterSearchesNameAndRegion(){val markers=listOf(MarkerEntity("a","Anemoculus","OCULUS","Mondstadt",.2,.2),MarkerEntity("b","Ore","RESOURCE","Liyue",.3,.3));assertEquals(1,Domain.filter(markers,"Mondstadt",null,"Anemo").size)}
}