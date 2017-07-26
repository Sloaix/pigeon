import com.lsxiao.pigeon.Pigeon
import junit.framework.TestCase
import java.io.File

/**
 * write with pigeon
 * author:lsxiao
 * date:2017-07-20 21:06
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */
class PigeonTest : TestCase() {

    /**
     *
     */
    fun testNewInstance() {

    }

    /**
     * 测试发布邮件
     */
    fun testSendEmail() {
        Pigeon.Builder
                .create()
                .from("faith.epiphone@qq.com")
                .to("faith.epiphone@qq.com")
                .username("faith.epiphone@qq.com")
                .password("")
                .subject("pigeon")
                .host("smtp.qq.com")
                .sll(true)
                .attach(Pigeon.Attachment(File("/Users/boluo/Desktop/研发部肖立尚201707020.xlsx")))
                .build()
                .toObservable()
                .subscribe({
                    println("发送成功")
                }, {
                    println(it)
                })
    }

}