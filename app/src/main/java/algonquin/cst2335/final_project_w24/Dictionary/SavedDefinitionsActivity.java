package algonquin.cst2335.final_project_w24.Dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


import algonquin.cst2335.final_project_w24.R;

/**
 * purpose of the file: SavedDefinitionsActivity shows the definitions of the saved word where the user can delete a definition
 * @author Tony Nsang
 * lab section: 022
 * creation date: March 28, 2023.
 */
public class SavedDefinitionsActivity extends AppCompatActivity {
    /**
     * RecycleView Adapter
     */
    private RecyclerView.Adapter myAdapter;

    /**
     * DTO
     */
    DictionaryData data;

    /**
     * DAO for DictionaryDatabase
     */
    DictionaryDAO mDAO;
    /**
     * Search Term from DictionaryActivity
     */
    String selectedWord;

    /**
     * On Create Method to Load the SavedDefinitionsActivity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_definitions);

        selectedWord = getIntent().getStringExtra("selected_word");
        ArrayList<String> definitions = getIntent().getStringArrayListExtra("definitions");


        TextView wordTextView = findViewById(R.id.savedWord);
        wordTextView.setText(selectedWord);

        if (definitions != null) {


            RecyclerView recyclerView = findViewById(R.id.savedDefinitions);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            DictionaryDatabase db = Room.databaseBuilder(getApplicationContext(), DictionaryDatabase.class, "dictionary-database").build();
            mDAO = db.stDAO();

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                        data = mDAO.getWordBySearchTerm(selectedWord);

                    });
        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder2>() {
            /**
             * This function creates a ViewHolder object. It represents a single row in the list
             * @param parent   The ViewGroup into which the new View will be added after it is bound to
             *                 an adapter position.
             * @param viewType The view type of the new View.
             * @return MyRowHolder2
             */
            @NonNull
            @Override
            public MyRowHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_delete_definitions, parent, false);
                return new MyRowHolder2(itemView);
            }

            /**
             * This initializes a ViewHolder to go at the row specified by the position parameter.
             * @param holder   The ViewHolder which should be updated to represent the contents of the
             *                 item at the given position in the data set.
             * @param position The position of the item within the adapter's data set.
             */
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder2 holder, int position) {
                // Bind data to views
                String definition = definitions.get(position);
                holder.definitionText.setText(definition);

                // On click listener to delete button
                holder.deleteButton.setOnClickListener(v -> {
                    confirmDeleteDefinition(definition, position);
                });
            }

            /**
             * This function just returns an int specifying how many items to draw.
             * @return int
             */
            @Override
            public int getItemCount() {
                return definitions.size();
            }
        });

        myAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No definitions found for " + selectedWord, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * an object for representing everything that goes on a row in the list
     */
    class MyRowHolder2 extends RecyclerView.ViewHolder {
        TextView definitionText;
        TextView deleteButton;

        public MyRowHolder2(@NonNull View itemView) {
            super(itemView);
            definitionText = itemView.findViewById(R.id.definitionTextView);
            deleteButton = itemView.findViewById(R.id.delete_definition_button);
        }
    }

    /**
     * Method that shows an AlertDialog to confirm a deletion of a particular definition
     * @param definition definition being deleted
     * @param position the position of the definition in the recycleview
     */
    private void confirmDeleteDefinition(String definition, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this definition?");
        builder.setPositiveButton("Yes", (dialog, cl) -> {
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                mDAO.deleteDefinition(selectedWord, definition);
                runOnUiThread(() -> {
                    // Remove the deleted definition from the list
                   String removed =  data.getDefinitionsOfTerm().remove(position);
                    // Notify adapter about the deletion
                    myAdapter.notifyItemRemoved(position);
                    // Show Snackbar for undo option
                    showUndoSnackbar(removed, position);
                });
            });
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
    }

    /**
     * Method to show a SnackBar to display deletion message and the option to undo the deletion
     * @param deletedDefinition deleted definition
     * @param position position of the deleted definition in the ArrayList of definitions
     */
    private void showUndoSnackbar(String deletedDefinition, int position) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                "Definition deleted", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", v -> {
            // Add the deleted definition back to the list
            data.getDefinitionsOfTerm().add(position, deletedDefinition);
            // Notify adapter about the addition
            myAdapter.notifyItemInserted(position);
        });
        snackbar.show();
    }
}
