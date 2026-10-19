package com.unitx.template.domain.unitError

import com.unitx.template.domain.unitError.errors.BuiltErrors
import com.unitx.template.domain.unitError.main.ResultUnit
import kotlinx.coroutines.flow.Flow

// Generic Surface types
typealias VoidResult<E> = ResultUnit<Unit, E>
typealias FlowArrayResult<D,E> = ResultUnit<Flow<List<D>>, E>
typealias FlowNullArrayResult<D,E> = ResultUnit<Flow<List<D>?>, E>
typealias ArrayResult<D,E> = ResultUnit<List<D>, E>
typealias FlowResult<D,E> = ResultUnit<Flow<D>, E>
typealias ObjectResult<D,E> = ResultUnit<D, E>

// Datastore
typealias FlowDatastore<D> = ResultUnit<Flow<D>, BuiltErrors.DataStoreError>
typealias FlowArrayDatastore<D> = ResultUnit<Flow<List<D>>, BuiltErrors.DataStoreError>
typealias FlowNullArrayDatastore<D> = ResultUnit<Flow<List<D>?>, BuiltErrors.DataStoreError>
typealias ArrayDatastore<D> = ResultUnit<List<D>, BuiltErrors.DataStoreError>
typealias ObjectDatastore<D> = ResultUnit<D, BuiltErrors.DataStoreError>
typealias UnitDatastore = ResultUnit<Unit, BuiltErrors.DataStoreError>

// Room
typealias FlowRoom<D> = ResultUnit<Flow<D>, BuiltErrors.RoomError>
typealias FlowArrayRoom<D> = ResultUnit<Flow<List<D>>, BuiltErrors.RoomError>
typealias FlowNullArrayRoom<D> = ResultUnit<Flow<List<D>?>, BuiltErrors.RoomError>
typealias ArrayRoom<D> = ResultUnit<List<D>, BuiltErrors.RoomError>
typealias ObjectRoom<D> = ResultUnit<D, BuiltErrors.RoomError>
typealias UnitRoom = ResultUnit<Unit, BuiltErrors.RoomError>

// Network
typealias FlowNetwork<D> = ResultUnit<Flow<D>, BuiltErrors.NetworkError>
typealias FlowArrayNetwork<D> = ResultUnit<Flow<List<D>>, BuiltErrors.NetworkError>
typealias FlowNullArrayNetwork<D> = ResultUnit<Flow<List<D>?>, BuiltErrors.NetworkError>
typealias ArrayNetwork<D> = ResultUnit<List<D>, BuiltErrors.NetworkError>
typealias ObjectNetwork<D> = ResultUnit<D, BuiltErrors.NetworkError>
typealias UnitNetwork = ResultUnit<Unit, BuiltErrors.NetworkError>

//// Cloud
//typealias FlowFirestore<D> = ResultUnit<Flow<D>, BuiltErrors.FirestoreError>
//typealias FlowArrayFirestore<D> = ResultUnit<Flow<List<D>>, BuiltErrors.FirestoreError>
//typealias FlowNullArrayFirestore<D> = ResultUnit<Flow<List<D>?>, BuiltErrors.FirestoreError>
//typealias ArrayFirestore<D> = ResultUnit<List<D>, BuiltErrors.FirestoreError>
//typealias ObjectFirestore<D> = ResultUnit<D, BuiltErrors.FirestoreError>
//typealias UnitFirestore = ResultUnit<Unit, BuiltErrors.FirestoreError>
//
//typealias VoidResultFirestoreAndRoom = ResultUnit<Unit, BuiltErrors.FirestoreAndRoom>
//typealias ObjectFirestoreAndRoom<D> = ResultUnit<D, BuiltErrors.FirestoreAndRoom>
//typealias ArrayFirestoreAndRoom<D> = ResultUnit<List<D>, BuiltErrors.FirestoreAndRoom>