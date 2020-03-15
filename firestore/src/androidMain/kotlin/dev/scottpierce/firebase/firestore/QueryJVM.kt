package dev.scottpierce.firebase.firestore

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.MetadataChanges

actual typealias Query = com.google.firebase.firestore.Query

actual fun Query.orderBy(
    field: String,
    direction: QueryDirection?
): Query =
    if (direction == null) {
        orderBy(field)
    } else {
        orderBy(
            field, if (direction == QueryDirection.ASCENDING) {
                com.google.firebase.firestore.Query.Direction.ASCENDING
            } else {
                com.google.firebase.firestore.Query.Direction.DESCENDING
            }
        )
    }

actual fun Query.limit(limit: Long): Query = limit(limit)
actual fun Query.get_(source: Source?): TaskData<QuerySnapshot> =
    TaskData(if(source != null){get(sourceToJvmSource(source))}else{get()})

actual val Query.firestore: FirebaseFirestore
    get() = firestore

actual fun Query.addSnapshotListener_(
    metadataChanges: dev.scottpierce.firebase.firestore.MetadataChanges?,
    listener: (QuerySnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration =
    if (metadataChanges == null) {
        addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            listener(
                querySnapshot,
                firebaseFirestoreException?.let { FirebaseFirestoreException(firebaseFirestoreException) })
        }
    } else {
        addSnapshotListener(
            metadataChanges.toJvm()
        ) { querySnapshot, firebaseFirestoreException ->
            listener(
                querySnapshot,
                firebaseFirestoreException?.let { FirebaseFirestoreException(firebaseFirestoreException) })
        }
    }

internal fun dev.scottpierce.firebase.firestore.MetadataChanges.toJvm():MetadataChanges =
    if (this == dev.scottpierce.firebase.firestore.MetadataChanges.INCLUDE) {
        MetadataChanges.INCLUDE
    } else {
        MetadataChanges.EXCLUDE
    }

actual fun Query.endAt(documentSnapshot: DocumentSnapshot): Query = endAt(documentSnapshot)
actual fun Query.endBefore(documentSnapshot: DocumentSnapshot): Query = endBefore(documentSnapshot)
actual fun Query.startAfter(documentSnapshot: DocumentSnapshot): Query = startAfter(documentSnapshot)
actual fun Query.startAt(documentSnapshot: DocumentSnapshot): Query = startAt(documentSnapshot)
actual fun Query.whereEqualTo(
    field: String,
    value: Any
): Query = whereEqualTo(field, value)

actual fun Query.whereGreaterThan(
    field: String,
    value: Any
): Query = whereGreaterThan(field, value)

actual fun Query.whereGreaterThanOrEqualTo(
    field: String,
    value: Any
): Query = whereGreaterThanOrEqualTo(field, value)

actual fun Query.whereLessThan(
    field: String,
    value: Any
): Query = whereLessThan(field, value)

actual fun Query.whereLessThanOrEqualTo(
    field: String,
    value: Any
): Query = whereLessThanOrEqualTo(field, value)

actual fun Query.whereArrayContains(
    field: String,
    value: Any
): Query = whereArrayContains(field, value)

actual fun Query.whereArrayContains(
    field: FieldPath,
    value: Any
): Query = whereArrayContains(field, value)