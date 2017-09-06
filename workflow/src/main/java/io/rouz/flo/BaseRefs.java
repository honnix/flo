package io.rouz.flo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A convenience class for holding some reference. This is only so that we don't have to repeat
 * these declaration in every class above.
 */
class BaseRefs<Z> {

  final Fn<List<Task<?>>> inputs;
  final Map<MetaKey<?>, Object> meta;
  final List<OpProvider<?>> ops;
  final TaskId taskId;
  protected final Class<Z> type;

  BaseRefs(TaskId taskId, Class<Z> type) {
    this(new HashMap<>(), Collections::emptyList, Collections.emptyList(), taskId, type);
  }

  BaseRefs(
      Map<MetaKey<?>, Object> meta,
      Fn<List<Task<?>>> inputs,
      List<OpProvider<?>> ops,
      TaskId taskId,
      Class<Z> type) {
    this.inputs = inputs;
    this.meta = meta;
    this.ops = ops;
    this.taskId = taskId;
    this.type = type;
  }
}
