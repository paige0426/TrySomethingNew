package com.paige.trysomethingnew.api.model

data class Restaurant(val id: String, val name: String)

//class RestaurantGsonConverterFactory private constructor(private val gson: Gson, private val type: Type) :
//    Converter.Factory() {
//    override fun responseBodyConverter(
//        type: Type,
//        annotations: Array<Annotation>,
//        retrofit: Retrofit
//    ): Converter<ResponseBody, *>? {
//        return RestaurantRsponseBodyConverter(gson, gson.getAdapter(TypeToken.get(this.type)))
//    }
//
//    companion object {
//        fun create(gson: Gson, type: Type) = RestaurantGsonConverterFactory(gson, type)
//    }
//}
//
//class RestaurantRsponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) :
//    Converter<ResponseBody, T> {
//    override fun convert(value: ResponseBody): T? {
//        val jsonReader = gson.newJsonReader(value.charStream())
//        try {
//            val result = adapter.read(jsonReader)
//            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
//                throw JsonIOException("JSON document was not fully consumed.")
//            }
//            return result
//        } finally {
//            value.close()
//        }
//    }
//
//}