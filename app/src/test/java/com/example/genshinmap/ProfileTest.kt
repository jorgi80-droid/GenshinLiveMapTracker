package com.example.genshinmap
import com.example.genshinmap.core.profile.TrackerProfile
import org.junit.Assert.*
import org.junit.Test
class ProfileTest{@Test fun profileValuesAreRetained(){val profile=TrackerProfile("id","Traveler",8,55,"Fontaine");assertEquals("Traveler",profile.name);assertEquals(55,profile.ar)}}