package com.thecoderscorner.groovychart.dataset.pie

import com.thecoderscorner.groovychart.dataset.BaseDatasetBuilder
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.Dataset

/**
 * generates a pie dataset using builder notation.
 */
class DefaultPieDatasetBuilder extends BaseDatasetBuilder {

    // provides the pie data set that backs this builder.
    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();

    public void processNode(Object name, Map map, Object value) {
        // can populate this dataset from a map.
        if(name == "fromMap" && value instanceof Map) {
            map.each { key, val ->
                dataset.setValue (key, val)
            }
        }
        else if(!name.toString().equalsIgnoreCase('defaultpiedataset')) {

            defaultPieDataset.setValue(name, value as Number);
        }

    }

    public Dataset getDataset() {
        return defaultPieDataset
    }

}