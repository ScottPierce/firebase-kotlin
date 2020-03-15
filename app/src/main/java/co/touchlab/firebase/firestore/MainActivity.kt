package dev.scottpierce.firebase.firestore

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope()  {

    private val flow = getFirebaseInstance().collection("testdata")
        .asFlow()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            flow.collect(object : FlowCollector<QuerySnapshot> {
                override suspend fun emit(value: QuerySnapshot) {
                    findViewById<ListView>(R.id.games).adapter =
                        ArrayAdapter<String>(
                            this@MainActivity,
                            android.R.layout.simple_list_item_1,
                            value.documents.map { it.data?.get("name") as String? }
                                .filterNotNull()
                        )

                }
            })
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            getFirebaseInstance().collection("testdata")
                .document().set(mapOf(Pair("name", "Some data ${System.currentTimeMillis()}")))
        }

        findViewById<Button>(R.id.docRefStuff).setOnClickListener {
            docRefStuff()
        }
    }

    private fun docRefStuff() = launch {
        val documentReference = getFirebaseInstance().collection("testdata")
            .document()
        val nameString = "Suspend data ${System.currentTimeMillis()}"
        documentReference.path

        documentReference.suspendSet(mapOf(Pair("name", nameString)))
        Toast.makeText(this@MainActivity, ((documentReference.suspendGet() as DocumentSnapshot)["name"] as String), Toast.LENGTH_LONG).show()
        delay(3000)
        documentReference.suspendDelete()

        readTests()
    }

    private suspend fun readTests() {
        val fb = getFirebaseInstance()
        val trcoll = fb.collection("testreads")
        assertEquals(trcoll.id, "testreads")
        val docRef = trcoll.document("s5SKxjMW8qxmqHT6gcJ8")
        assertEquals(docRef.id, "s5SKxjMW8qxmqHT6gcJ8")
        val docSnap = docRef.suspendGet()
        assertEquals(docSnap.getLong("aval"), 123.toLong())
        assertEquals(docSnap.getDouble("aval"), 123.toDouble())
        assertEquals(docSnap.getBoolean("abool"), true)
        Toast.makeText(this@MainActivity, "Snap name ${docSnap.getString("name")}", Toast.LENGTH_LONG).show()
    }

    private fun assertEquals(a:Any?, b:Any?){
        if(a != b)
            throw IllegalStateException("nah $a == $b")
    }
}
