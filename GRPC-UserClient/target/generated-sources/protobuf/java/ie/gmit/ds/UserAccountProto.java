// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserAccountProto.proto

package ie.gmit.ds;

public final class UserAccountProto {
  private UserAccountProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ie_gmit_ds_HashRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ie_gmit_ds_HashRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ie_gmit_ds_HashResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ie_gmit_ds_HashResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ie_gmit_ds_ValidationRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ie_gmit_ds_ValidationRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026UserAccountProto.proto\022\nie.gmit.ds\032\036go" +
      "ogle/protobuf/wrappers.proto\"/\n\013HashRequ" +
      "est\022\016\n\006userId\030\001 \001(\005\022\020\n\010password\030\002 \001(\t\"D\n" +
      "\014HashResponse\022\016\n\006userId\030\001 \001(\005\022\026\n\016hashedP" +
      "assword\030\002 \001(\014\022\014\n\004salt\030\003 \001(\014\"K\n\021Validatio" +
      "nRequest\022\020\n\010password\030\001 \001(\t\022\026\n\016hashedPass" +
      "word\030\002 \001(\014\022\014\n\004salt\030\003 \001(\0142\223\001\n\017PasswordSer" +
      "vice\0229\n\004hash\022\027.ie.gmit.ds.HashRequest\032\030." +
      "ie.gmit.ds.HashResponse\022E\n\010validate\022\035.ie" +
      ".gmit.ds.ValidationRequest\032\032.google.prot" +
      "obuf.BoolValueB\016\n\nie.gmit.dsP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
        });
    internal_static_ie_gmit_ds_HashRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ie_gmit_ds_HashRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ie_gmit_ds_HashRequest_descriptor,
        new java.lang.String[] { "UserId", "Password", });
    internal_static_ie_gmit_ds_HashResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ie_gmit_ds_HashResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ie_gmit_ds_HashResponse_descriptor,
        new java.lang.String[] { "UserId", "HashedPassword", "Salt", });
    internal_static_ie_gmit_ds_ValidationRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ie_gmit_ds_ValidationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ie_gmit_ds_ValidationRequest_descriptor,
        new java.lang.String[] { "Password", "HashedPassword", "Salt", });
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}