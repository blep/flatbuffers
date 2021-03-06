// automatically generated by the FlatBuffers compiler, do not modify

package reflection;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

@SuppressWarnings("unused")
public final class Object extends Table {
  public static Object getRootAsObject(ByteBuffer _bb) { return getRootAsObject(_bb, new Object()); }
  public static Object getRootAsObject(ByteBuffer _bb, Object obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Object __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public Field fields(int j) { return fields(new Field(), j); }
  public Field fields(Field obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int fieldsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public boolean isStruct() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public int minalign() { int o = __offset(10); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int bytesize() { int o = __offset(12); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public KeyValue attributes(int j) { return attributes(new KeyValue(), j); }
  public KeyValue attributes(KeyValue obj, int j) { int o = __offset(14); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int attributesLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }

  public static int createObject(FlatBufferBuilder builder,
      int nameOffset,
      int fieldsOffset,
      boolean is_struct,
      int minalign,
      int bytesize,
      int attributesOffset) {
    builder.startObject(6);
    Object.addAttributes(builder, attributesOffset);
    Object.addBytesize(builder, bytesize);
    Object.addMinalign(builder, minalign);
    Object.addFields(builder, fieldsOffset);
    Object.addName(builder, nameOffset);
    Object.addIsStruct(builder, is_struct);
    return Object.endObject(builder);
  }

  public static void startObject(FlatBufferBuilder builder) { builder.startObject(6); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addFields(FlatBufferBuilder builder, int fieldsOffset) { builder.addOffset(1, fieldsOffset, 0); }
  public static int createFieldsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startFieldsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addIsStruct(FlatBufferBuilder builder, boolean isStruct) { builder.addBoolean(2, isStruct, false); }
  public static void addMinalign(FlatBufferBuilder builder, int minalign) { builder.addInt(3, minalign, 0); }
  public static void addBytesize(FlatBufferBuilder builder, int bytesize) { builder.addInt(4, bytesize, 0); }
  public static void addAttributes(FlatBufferBuilder builder, int attributesOffset) { builder.addOffset(5, attributesOffset, 0); }
  public static int createAttributesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startAttributesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endObject(FlatBufferBuilder builder) {
    int o = builder.endObject();
    builder.required(o, 4);  // name
    builder.required(o, 6);  // fields
    return o;
  }

  @Override
  protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) { return compareStrings(__offset(4, o1, _bb), __offset(4, o2, _bb), _bb); }

  public static Object lookupByKey(int vectorOffset, String key, ByteBuffer bb) {
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
        return new Object().__init(tableOffset, bb);
      }
    }
    return null;
  }


  // added manually for the moment open to discussion :)
  public Field field(String key) {
    int vectorOffset = __vector(__offset(6)) - 4;
    return vectorOffset != 0 ? Field.lookupByKey(bb.array().length - vectorOffset, key, bb) : null;
  }
}

