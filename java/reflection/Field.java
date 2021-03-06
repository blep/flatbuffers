// automatically generated by the FlatBuffers compiler, do not modify

package reflection;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

@SuppressWarnings("unused")
public final class Field extends Table {
  public static Field getRootAsField(ByteBuffer _bb) { return getRootAsField(_bb, new Field()); }
  public static Field getRootAsField(ByteBuffer _bb, Field obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Field __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public Type type() { return type(new Type()); }
  public Type type(Type obj) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  public int id() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public int offset() { int o = __offset(10); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public long defaultInteger() { int o = __offset(12); return o != 0 ? bb.getLong(o + bb_pos) : 0; }
  public double defaultReal() { int o = __offset(14); return o != 0 ? bb.getDouble(o + bb_pos) : 0.0; }
  public boolean deprecated() { int o = __offset(16); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public boolean required() { int o = __offset(18); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public boolean key() { int o = __offset(20); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public KeyValue attributes(int j) { return attributes(new KeyValue(), j); }
  public KeyValue attributes(KeyValue obj, int j) { int o = __offset(22); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int attributesLength() { int o = __offset(22); return o != 0 ? __vector_len(o) : 0; }

  public static int createField(FlatBufferBuilder builder,
      int nameOffset,
      int typeOffset,
      int id,
      int offset,
      long default_integer,
      double default_real,
      boolean deprecated,
      boolean required,
      boolean key,
      int attributesOffset) {
    builder.startObject(10);
    Field.addDefaultReal(builder, default_real);
    Field.addDefaultInteger(builder, default_integer);
    Field.addAttributes(builder, attributesOffset);
    Field.addType(builder, typeOffset);
    Field.addName(builder, nameOffset);
    Field.addOffset(builder, offset);
    Field.addId(builder, id);
    Field.addKey(builder, key);
    Field.addRequired(builder, required);
    Field.addDeprecated(builder, deprecated);
    return Field.endField(builder);
  }

  public static void startField(FlatBufferBuilder builder) { builder.startObject(10); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addType(FlatBufferBuilder builder, int typeOffset) { builder.addOffset(1, typeOffset, 0); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addShort(2, (short)id, 0); }
  public static void addOffset(FlatBufferBuilder builder, int offset) { builder.addShort(3, (short)offset, 0); }
  public static void addDefaultInteger(FlatBufferBuilder builder, long defaultInteger) { builder.addLong(4, defaultInteger, 0); }
  public static void addDefaultReal(FlatBufferBuilder builder, double defaultReal) { builder.addDouble(5, defaultReal, 0.0); }
  public static void addDeprecated(FlatBufferBuilder builder, boolean deprecated) { builder.addBoolean(6, deprecated, false); }
  public static void addRequired(FlatBufferBuilder builder, boolean required) { builder.addBoolean(7, required, false); }
  public static void addKey(FlatBufferBuilder builder, boolean key) { builder.addBoolean(8, key, false); }
  public static void addAttributes(FlatBufferBuilder builder, int attributesOffset) { builder.addOffset(9, attributesOffset, 0); }
  public static int createAttributesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startAttributesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endField(FlatBufferBuilder builder) {
    int o = builder.endObject();
    builder.required(o, 4);  // name
    builder.required(o, 6);  // type
    return o;
  }

  @Override
  protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) { return compareStrings(__offset(4, o1, _bb), __offset(4, o2, _bb), _bb); }

  public static Field lookupByKey(int vectorOffset, String key, ByteBuffer bb) {
    byte[] byteKey = key.getBytes(Table.UTF8_CHARSET.get());
    int vectorLocation = bb.array().length - vectorOffset;
    int span = bb.getInt(vectorLocation);
    int start = 0;
    vectorLocation += 4;
    while (span != 0) {
      int middle = span / 2;
      int tableOffset = __indirect(vectorLocation + 4 * (start + middle), bb);
      int comp = compareStrings(__offset(4, bb.array().length - tableOffset, bb), byteKey, bb);
      if (comp > 0) {
        span = middle;
      } else if (comp < 0) {
        middle++;
        start += middle;
        span -= middle;
      } else {
        return new Field().__init(tableOffset, bb);
      }
    }
    return null;
  }
}

