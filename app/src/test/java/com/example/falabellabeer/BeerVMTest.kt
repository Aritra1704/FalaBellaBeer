package com.example.falabellabeer

import com.example.falabellabeer.viewmodel.BeerVM
import org.junit.Assert
import org.junit.Test

class BeerVMTest {
    internal var beerVm = BeerVM()
    @Test
    fun nextFilterTest() {
        val expected = "LAGER"
        val actual = beerVm.nextFilter("ALE")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun filterTest() {
        val expected = "LAGER"
        val actual = beerVm.filter("ALE")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun sortTest() {
        val expected = "LAGER"
        val actual = beerVm.sort(true)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun searchTest() {
        val expected = "LAGER"
        val actual = beerVm.search("Ame")
        Assert.assertEquals(expected, actual)
    }
}