package dev.scottpierce.firebase.firestore

expect class SnapshotMetadata

expect val SnapshotMetadata.pendingWrites: Boolean
expect val SnapshotMetadata.fromCache: Boolean
