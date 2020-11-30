/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

/**
 *
 * @author Thewbacca
 */
public interface InterfaceItem {
     /**
     * Interface source code revision.
     */
    String DSP_INTERFACE_CODE_REVISION = "$Revision: 1.4 $";

    /**
     * Abstract feature extraction routine.
     *
     * @return boolean true if there were features extracted, false otherwise
     * @throws FeatureExtractionException if there was an error while extracting features
     */
    //boolean extractFeatures()
    //        throws FeatureExtractionException;

    /**
     * Allows retrieval of the features.
     *
     * @return array of features (<code>double</code> values)
     */
    double[] getPriceArray();

    /**
     * Retrieves inner preprocessing reference.
     *
     * @return the preprocessing reference
     * @since 0.3.0.4
     */
    InterfaceItem getPreprocessing();

    /**
     * Allows setting the source preprocessing module.
     *
     * @param poItem the preprocessing object to set
     * @since 0.3.0.4
     */
    void setPreprocessing(InterfaceItem poItem);
}
