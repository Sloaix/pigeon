import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.MultiPartEmail
import java.io.File
import java.util.*
import javax.mail.internet.MimeUtility

/**
 * write with pigeon
 * author:lsxiao
 * date:2017-07-20 12:21
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 *
 * Mailing for human beings.
 *
 */
class Pigeon {
    private var sll: Boolean = false
    private var host: String? = null
    private var port: Int? = null
    private var charset: String? = null
    private var senderName: String? = null
    private var senderEmail: String? = null
    private var receiverEmail: String? = null
    private var username: String? = null
    private var password: String? = null
    private var subject: String? = null
    private var content: String? = null
    private lateinit var attachments: List<Attachment>

    fun send() {

        val mail = MultiPartEmail()
        mail.hostName = host
        mail.isSSLOnConnect = sll
        mail.setCharset(charset)
        mail.addTo(receiverEmail)
        mail.setFrom(senderEmail, senderName)
        mail.setAuthentication(username, password)

        if (!content.isNullOrEmpty()) {
            mail.setMsg(content)
        }

        mail.subject = subject

        attachments.forEach { (file, name, _, description) ->
            val attachment = EmailAttachment()
            // 设置附件路径
            attachment.path = file.path
            attachment.disposition = EmailAttachment.ATTACHMENT
            // 附件描述
            attachment.description = description
            val finalName = when {
                name.isNullOrEmpty() -> file.name
                else -> name
            }
            attachment.name = MimeUtility.encodeWord(finalName, MimeUtility.getDefaultJavaCharset(), "B")
            mail.attach(attachment)
        }

        mail.send()

    }

    data class Attachment(val file: File, val name: String? = null, val disposition: String = EmailAttachment.ATTACHMENT, val description: String? = null)

    class Builder private constructor() {
        private var sll = false
        private lateinit var host: String
        private var port: Int = 25
        private var charset: String = "utf-8"
        private var senderName: String = ""
        private lateinit var senderEmail: String
        private lateinit var receiverEmail: String
        private lateinit var username: String
        private lateinit var password: String
        private var subject: String = ""
        private var content: String = ""
        private val attachments = ArrayList<Attachment>()

        fun sll(sll: Boolean): Builder {
            this.sll = sll
            return this
        }

        fun host(host: String): Builder {
            this.host = host
            return this
        }

        fun port(port: Int): Builder {
            this.port = port
            return this
        }

        fun charset(charset: String): Builder {
            this.charset = charset
            return this
        }

        fun showname(senderName: String): Builder {
            this.senderName = senderName
            return this
        }

        fun from(senderEmail: String): Builder {
            this.senderEmail = senderEmail
            return this
        }

        fun to(receiverEmail: String): Builder {
            this.receiverEmail = receiverEmail
            return this
        }

        fun username(username: String): Builder {
            this.username = username
            return this
        }

        fun password(password: String): Builder {
            this.password = password
            return this
        }

        fun subject(subject: String): Builder {
            this.subject = subject
            return this
        }

        fun content(content: String): Builder {
            this.content = content
            return this
        }

        fun attach(attachment: Attachment): Builder {
            this.attachments.add(attachment)
            return this
        }

        fun build(): Pigeon {
            val pigeon = Pigeon()
            pigeon.sll = this.sll
            pigeon.charset = this.charset
            pigeon.port = this.port
            pigeon.subject = this.subject
            pigeon.content = this.content
            pigeon.senderName = this.senderName
            pigeon.attachments = this.attachments
            pigeon.senderEmail = this.senderEmail
            pigeon.receiverEmail = this.receiverEmail
            pigeon.username = this.username
            pigeon.host = this.host
            pigeon.password = this.password
            return pigeon
        }

        companion object {

            @JvmStatic fun create(): Builder {
                return Builder()
            }
        }
    }
}
