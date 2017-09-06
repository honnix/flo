package io.rouz.flo;

/**
 * A type-safe key for metadata key-value pairs that can be added to a {@link Task}.
 *
 * @param <E> The type of the values associated with this key
 */
public interface MetaKey<E> {

  String name();

  /**
   * Should be used to create metadata entries from operators in {@link OpProvider#provideMeta()}.
   *
   * @param value The value for the created entry
   * @return A metadata entry
   */
  default Entry<E> value(E value) {
    return new Entry<E>() {
      @Override
      public MetaKey<E> key() {
        return MetaKey.this;
      }

      @Override
      public E value() {
        return value;
      }
    };
  }

  /**
   * Create a task metadata key with a given name. Use this static constructor to create key names.
   *
   * <p>Keys should ideally be created as static constants.
   *
   * <pre>{@code
   * public static final MetaKey<String> SOME_METADATA = MetaKey.create("Metadata foo");
   * }</pre>
   */
  static <E> MetaKey<E> create(String name) {
    return () -> name;
  }

  /**
   * A type-safe key-value pair for some {@link MetaKey}. These can be created by calling
   * {@link MetaKey#value(Object)}.
   *
   * @param <E> The type of the value
   */
  interface Entry<E> {
    MetaKey<E> key();
    E value();
  }
}
