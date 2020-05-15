package br.com.adolfobocchi.meuqueridoprofessor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import br.com.adolfobocchi.meuqueridoprofessor.R;
import br.com.adolfobocchi.meuqueridoprofessor.config.ConfiguracaoFirebase;
import br.com.adolfobocchi.meuqueridoprofessor.model.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private TextView txtNome, txtEmail;
    private CircleImageView circleImageView;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        circleImageView = findViewById(R.id.imgProfile);
        recuperarUsuario();
    }

    public void logout(View view) {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        finish();
    }

    public void recuperarUsuario() {
        DatabaseReference usuarioRef = ConfiguracaoFirebase.getFirebaseDatabase().child("usuarios");
        usuarioRef.child(ConfiguracaoFirebase.getIdentificadorUsuario())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        usuario = dataSnapshot.getValue(Usuario.class);
                        txtNome.setText(usuario.getNome());
                        txtEmail.setText(usuario.getEmail());
                        Picasso.get().load(usuario.getFoto()).into(circleImageView);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
