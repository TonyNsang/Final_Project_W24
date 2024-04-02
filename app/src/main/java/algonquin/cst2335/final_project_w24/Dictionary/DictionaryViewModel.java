package algonquin.cst2335.final_project_w24.Dictionary;

import android.widget.Button;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
/**
 * purpose of the file: ViewModel class for DictionaryActivity
 * To avoid Android from deleting the current screen and creating a new one for the new rotated orientation.
 * author: Tony Nsang
 * lab section: 022
 * creation date: March 28, 2023.
 */
/**
 * ViewModel class for DictionaryActivity
 * To avoid Android from deleting the current screen and creating a new one for the new rotated orientation.
 */
public class DictionaryViewModel extends ViewModel {
    public MutableLiveData<String> searchText = new MutableLiveData<>();

    public MutableLiveData<ArrayList<String>> definitions = new MutableLiveData< >();

}
