package mx.edu.utng.orders;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import mx.edu.utng.orders.adapters.SourceAdapter;
import mx.edu.utng.orders.model.Source;
import mx.edu.utng.orders.sqlite.DBOperations;
import java.util.ArrayList;
import java.util.List;


public class SourceActivity extends AppCompatActivity {

    private EditText etNameSource;
    private EditText etTypeSource;
    private Button btSaveSource;
    private DBOperations operations;
    private Source source = new Source();
    private List<Source> sources = new ArrayList<Source>();
    private SourceAdapter adapter;
    private RecyclerView rvSources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        operations = DBOperations.getDBOperations(getApplicationContext());
        source.setIdSource("");
        initComponents();
    }

    private void initComponents(){
        etNameSource = (EditText)findViewById(R.id.et_name_source);
        etTypeSource = (EditText)findViewById(R.id.et_type_source);
        rvSources = (RecyclerView) findViewById(R.id.rv_source_list);
        rvSources.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvSources.setLayoutManager(layoutManager);
        getSources();


        adapter = new SourceAdapter(sources);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.bt_delete_source:
                        operations.deleteSources(
                                sources.get(rvSources.getChildPosition((View)v.getParent().getParent())).getIdSource());
                        getSources();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_source:

                        Cursor c = operations.getSourcesById(sources.get(rvSources.getChildPosition((View)v.getParent().getParent()))
                                .getIdSource());

                        if(c!=null){
                            if(c.moveToFirst()){
                                source = new Source(c.getString(1),
                                        c.getString(2),c.getString(3));
                            }
                            etNameSource.setText(source.getNameSource());
                            etTypeSource.setText(String.valueOf(source.getTypeSource()));
                        }else{
                            //Toast.makeText(getApplicationContext(),
                            //      "Registro no encontrado", Toast)
                        }

                        break;
                    default:
                        break;

                }


                //operations.deleteProduct(products.get(rvProducts.getChildPosition(v))
                //        .getIdProduct());
                //products.remove(rvProducts.getChildPosition(v));
                //adapter.notifyDataSetChanged();
            }
        });
        rvSources.setAdapter(adapter);
        btSaveSource = (Button) findViewById(R.id.bt_save_source);



        btSaveSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!source.getIdSource().equals("")) {
                    source.setNameSource(etNameSource.getText().toString());
                    source.setTypeSource(etTypeSource.getText().toString());
                    operations.updateSources(source);
                } else {
                    source = new Source("", etNameSource.getText().toString(),
                            etTypeSource.getText().toString());
                    operations.insertSources(source);
                }
                // Log.d("Products","Products");
                // DatabaseUtils.dumpCursor(operations.getProducts());
                clearData();
                getSources();
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void getSources(){

        Cursor  c = operations.getSources();
        sources.clear();
        if(c!=null){
            while (c.moveToNext()){
                sources.add(new Source(c.getString(1),
                        c.getString(2),c.getString(3)));

            }
        }
    }

    private void clearData(){
        etNameSource.setText("");
        etTypeSource.setText("");
        source = null;
        source = new Source();
        source.setIdSource("");
    }

}
