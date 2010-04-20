package com.thecoderscorner.groovychart.dataset.series.xy.interval

import java.lang.reflect.Constructor
import org.jfree.data.general.Dataset
import org.jfree.data.time.TimeSeries
import org.jfree.data.time.TimeSeriesCollection
import org.jfree.data.time.TimeSeriesDataItem

/**
* normalizes all series in the collection such that they start at the same time
* as the first series in the collection, but are otherwise at the same relative
* position in time. This is useful for comparing multiple TimeSeries.
*
* other than the behavior above, this builder behaves like TimeSeriesCollectionBuilder
* the normalization only happens when getDataset is called... no internal properties
* are modified... the normalization is not cached.
*
* if your collection only contains one series, this is basically a noop
 * @author DanB
*/
class NormalizedTimeSeriesCollectionBuilder extends TimeSeriesCollectionBuilder {
    public NormalizedTimeSeriesCollectionBuilder() { }

    public Dataset getDataset() {
        return normalize(getCollection());
    }

    protected TimeSeriesCollection normalize(TimeSeriesCollection collection) {
        // we don't need to do anything for less than two series.
        if(collection.seriesCount < 2) return collection

        // this is soon to be the normalized data set we will return
        TimeSeriesCollection adjustedSeriesCollection = new TimeSeriesCollection()

        // this is target start time.. we'll set it in the following loop
        Long timeToNormalizeTo = null;
        // figure out the time to normalize to, and normalize all but the first series.
        collection.getSeries().each {TimeSeries series ->
            if (timeToNormalizeTo == null) {
                // the first series in the collection is the one we adjust all the others to match
                timeToNormalizeTo = series.getDataItem(0).period.start.time
                adjustedSeriesCollection.addSeries(series);
            } else {
                // create a series like the one we are adjusting with no data items
                // clone causes deep copy.. so we have to get one item in our copy then clear it.
                TimeSeries adjSeries = series.createCopy(0,0)
                adjSeries.clear();

                // we need the time of the first item in this series to normalize it
                long firstSeriesItemStartTime = series.getDataItem(0).period.start.time

                // we want to construct the same RegularTimePeriod type as the series we're normalizing
                Constructor timePeriodConstructor = Class.forName(series.getTimePeriodClass().name).getConstructor(Date.class);

                // iterate all the items, adjust the time, add it to the new series
                series.items.each {TimeSeriesDataItem itemToNormalize ->
                    // construct a Period class of the same type as the one we're adjusting and normalize it to
                    // fix the series time range to that of the first series in the collection
                    Date normalizedTime = new Date(timeToNormalizeTo + (itemToNormalize.period.start.time - firstSeriesItemStartTime))
                    adjSeries.add(timePeriodConstructor.newInstance(normalizedTime), itemToNormalize.value)
                }

                // add the new series to the new collection
                adjustedSeriesCollection.addSeries(adjSeries)
            }
        }
        return adjustedSeriesCollection;
    }
}