package com.example.apphoquei

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*


class ChatActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.title = "Chat"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        RecyclerViewChat.adapter = adapter

        VerificarLoginUtilizador()

        listenForMessages()

        botaoEnviar.setOnClickListener {
            EnviarMensagem()
        }

        TextChat.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                RecyclerViewChat.scrollToPosition(adapter.itemCount)
            }
        }
    }

    private fun listenForMessages() {
        val ref = FirebaseDatabase.getInstance().getReference("/mensagens")

        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)

                if (chatMessage != null) {
                    RecyclerViewChat.scrollToPosition(adapter.itemCount - 1)

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        adapter.add(ChatToItem(chatMessage.text))
                    } else {
                        adapter.add(ChatFromItem(chatMessage.text))
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }

    private fun VerificarLoginUtilizador() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            botaoEnviar.setBackgroundResource(R.drawable.rounded_button_red)
            botaoEnviar.isEnabled = false
            botaoEnviar.setTextColor(Color.WHITE)
            TextChat.isEnabled = false
            Toast.makeText(this, "Iniciar sessão para enviar mensagem", Toast.LENGTH_LONG).show()
        }
    }

    private fun EnviarMensagem() {
        val text = TextChat.text.toString()

        val fromId = FirebaseAuth.getInstance().uid ?: return

        val reference = FirebaseDatabase.getInstance().getReference("/mensagens").push()
        val chatMessage = ChatMessage(reference.key!!, text, fromId)
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                TextChat.text.clear()
                RecyclerViewChat.scrollToPosition(adapter.itemCount - 1)
            }
    }
}

class ChatFromItem(val text: String): Item<GroupieViewHolder>() {
    override  fun bind(groupieViewHolder: GroupieViewHolder, position: Int) {
        groupieViewHolder.itemView.textView_from_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}

class ChatToItem(val text: String): Item<GroupieViewHolder>() {
    override  fun bind(groupieViewHolder: GroupieViewHolder, position: Int) {
        groupieViewHolder.itemView.textView_to_row.text = text

    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }
}