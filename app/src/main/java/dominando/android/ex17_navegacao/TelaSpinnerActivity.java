package dominando.android.ex17_navegacao;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class TelaSpinnerActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    private static final String OPCAO_ATUAL = "opcaoAtual";
    Toolbar mToolbar;
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                getResources().getStringArray(R.array.secoes));
        mSpinner = new Spinner(this);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.addView(mSpinner);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState.containsKey(OPCAO_ATUAL)){
            mSpinner.setSelection(savedInstanceState.getInt(OPCAO_ATUAL));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(OPCAO_ATUAL, mSpinner.getSelectedItemPosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_spinner, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        exibirItem(position);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void exibirItem(int position) {
        String[] titulosAbas = getResources().getStringArray(R.array.secoes);
        TypedArray bgColors = getResources().obtainTypedArray(R.array.cores_bg);
        TypedArray textColors = getResources().obtainTypedArray(R.array.cores_texto);

        SegundoNivelFragment fragment =
                SegundoNivelFragment.novaInstancia(titulosAbas[position],
                        bgColors.getColor(position, 0),
                        textColors.getColor(position, 0));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }

}
