import java.io.File

fun main(args: Array<String>) {
    Pigeon.Builder
            .create()
            .from("faith.epiphone@qq.com")
            .to("faith.epiphone@qq.com")
            .username("faith.epiphone@qq.com")
            .password("kotpisbzntplbgha")
            .subject("pigeon")
            .host("smtp.qq.com")
            .sll(true)
            .attach(Pigeon.Attachment(File("/Users/boluo/Desktop/研发部肖立尚201707020.xlsx")))
            .build()
            .send()
}
