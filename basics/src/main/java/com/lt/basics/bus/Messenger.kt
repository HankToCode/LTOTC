package com.lt.basics.bus

import com.lt.basics.binding.command.BindingAction
import com.lt.basics.binding.command.BindingConsumer
import java.lang.reflect.Type
import java.util.*

/**
 * //TODO 这块kotlin改的还不算成功，需要检验实施后才能定义是否改善完整了
 * About : kelin的Messenger
 */
class Messenger {
    private var recipientsOfSubclassesAction: HashMap<Type, MutableList<WeakActionAndToken>?>? = null
    private var recipientsStrictAction: HashMap<Type, MutableList<WeakActionAndToken>?>? = null
    /**
     * @param recipient the receiver,if register in activity the recipient always set "this",
     * and "WeakMessenger.getDefault().unregister(this)" in onDestroy,if in ViewModel,
     * you can also register with Activity context and also in onDestroy to unregister.
     * @param action    do something on message received
     */
    fun register(recipient: Any?, action: BindingAction?) {
        register(recipient, null, false, action)
    }

    /**
     * @param recipient                 the receiver,if register in activity the recipient always set "this",
     * and "WeakMessenger.getDefault().unregister(this)" in onDestroy,if in ViewModel,
     * you can also register with Activity context and also in onDestroy to unregister.
     * @param receiveDerivedMessagesToo whether Derived class of recipient can receive the message
     * @param action                    do something on message received
     */
    fun register(recipient: Any?, receiveDerivedMessagesToo: Boolean, action: BindingAction?) {
        register(recipient, null, receiveDerivedMessagesToo, action)
    }

    /**
     * @param recipient the receiver,if register in activity the recipient always set "this",
     * and "WeakMessenger.getDefault().unregister(this)" in onDestroy,if in ViewModel,
     * you can also register with Activity context and also in onDestroy to unregister.
     * @param token     register with a unique token,when a messenger send a msg with same token,it
     * will
     * receive this msg
     * @param action    do something on message received
     */
    fun register(recipient: Any?, token: Any?, action: BindingAction?) {
        register(recipient, token, false, action)
    }

    /**
     * @param recipient                 the receiver,if register in activity the recipient always set "this",
     * and "WeakMessenger.getDefault().unregister(this)" in onDestroy,if in ViewModel,
     * you can also register with Activity context and also in onDestroy to unregister.
     * @param token                     register with a unique token,when a messenger send a msg with same token,it
     * will
     * receive this msg
     * @param receiveDerivedMessagesToo whether Derived class of recipient can receive the message
     * @param action                    do something on message received
     */
    fun register(recipient: Any?, token: Any?, receiveDerivedMessagesToo: Boolean, action: BindingAction?) {
        val messageType: Type = NotMsgType::class.java
        val recipients: HashMap<Type, MutableList<WeakActionAndToken>?>?
        if (receiveDerivedMessagesToo) {
            if (recipientsOfSubclassesAction == null) {
                recipientsOfSubclassesAction = HashMap()
            }
            recipients = recipientsOfSubclassesAction
        } else {
            if (recipientsStrictAction == null) {
                recipientsStrictAction = HashMap()
            }
            recipients = recipientsStrictAction
        }
        val list: MutableList<WeakActionAndToken>?
        if (!recipients!!.containsKey(messageType)) {
            list = ArrayList()
            recipients!![messageType] = list
        } else {
            list = recipients!![messageType]
        }
        val weakAction: WeakAction<Any> = WeakAction(recipient, action)
        val item = WeakActionAndToken(weakAction, token)
        list!!.add(item)
        cleanup()
    }

    /**
     * @param recipient {}
     * @param tClass    class of T
     * @param action    this action has one params that type of tClass
     * @param <T>       message data type
    </T> */
    fun <T : Any> register(recipient: Any?, tClass: Class<T>, action: BindingConsumer<T>?) {
        register(recipient, null, false, action, tClass)
    }

    /**
     * see {}
     *
     * @param recipient                 receiver of message
     * @param receiveDerivedMessagesToo whether derived class of recipient can receive the message
     * @param tClass                    class of T
     * @param action                    this action has one params that type of tClass
     * @param <T>                       message data type
    </T> */
    fun <T : Any> register(recipient: Any?, receiveDerivedMessagesToo: Boolean, tClass: Class<T>, action: BindingConsumer<T>?) {
        register(recipient, null, receiveDerivedMessagesToo, action, tClass)
    }

    /**
     * see {}
     *
     * @param recipient receiver of message
     * @param token     register with a unique token,when a messenger send a msg with same token,it
     * will
     * receive this msg
     * @param tClass    class of T for BindingConsumer
     * @param action    this action has one params that type of tClass
     * @param <T>       message data type
    </T> */
    fun <T : Any> register(recipient: Any?, token: Any?, tClass: Class<T>, action: BindingConsumer<T>?) {
        register(recipient, token, false, action, tClass)
    }

    /**
     * see {}
     *
     * @param recipient                 receiver of message
     * @param token                     register with a unique token,when a messenger send a msg with same token,it
     * will
     * receive this msg
     * @param receiveDerivedMessagesToo whether derived class of recipient can receive the message
     * @param action                    this action has one params that type of tClass
     * @param tClass                    class of T for BindingConsumer
     * @param <T>                       message data type
    </T> */
    fun <T : Any> register(recipient: Any?, token: Any?, receiveDerivedMessagesToo: Boolean, action: BindingConsumer<T>?, tClass: Class<T>) {
        val messageType: Type = tClass
        val recipients: HashMap<Type, MutableList<WeakActionAndToken>?>
        if (receiveDerivedMessagesToo) {
            if (recipientsOfSubclassesAction == null) {
                recipientsOfSubclassesAction = HashMap()
            }
            recipients = recipientsOfSubclassesAction!!
        } else {
            if (recipientsStrictAction == null) {
                recipientsStrictAction = HashMap()
            }
            recipients = recipientsStrictAction!!
        }
        val list: MutableList<WeakActionAndToken>?
        if (!recipients.containsKey(messageType)) {
            list = ArrayList()
            recipients[messageType] = list
        } else {
            list = recipients[messageType]
        }
        val weakAction: WeakAction<Any> = WeakAction(recipient, action) as WeakAction<Any>
        val item = WeakActionAndToken(weakAction, token)
        list!!.add(item)
        cleanup()
    }

    private fun cleanup() {
        cleanupList(recipientsOfSubclassesAction)
        cleanupList(recipientsStrictAction)
    }

    /**
     * @param token send with a unique token,when a receiver has register with same token,it will
     * receive this msg
     */
    fun sendNoMsg(token: Any?) {
        sendToTargetOrType(null, token)
    }

    /**
     * send to recipient directly with has not any message
     *
     * @param target WeakMessenger.getDefault().register(this, ..) in a activity,if target set this
     * activity
     * it will receive the message
     */
    fun sendNoMsgToTarget(target: Any) {
        sendToTargetOrType(target.javaClass, null)
    }

    /**
     * send message to target with token,when a receiver has register with same token,it will
     * receive this msg
     *
     * @param token  send with a unique token,when a receiver has register with same token,it will
     * receive this msg
     * @param target send to recipient directly with has not any message,
     * WeakMessenger.getDefault().register(this, ..) in a activity,if target set this activity
     * it will receive the message
     */
    fun sendNoMsgToTargetWithToken(token: Any?, target: Any) {
        sendToTargetOrType(target.javaClass, token)
    }

    /**
     * send the message type of T, all receiver can receive the message
     *
     * @param message any object can to be a message
     * @param <T>     message data type
    </T> */
    fun <T : Any> send(message: T) {
        sendToTargetOrType(message, null, null)
    }

    /**
     * send the message type of T, all receiver can receive the message
     *
     * @param message any object can to be a message
     * @param token   send with a unique token,when a receiver has register with same token,it will
     * receive this message
     * @param <T>     message data type
    </T> */
    fun <T : Any> send(message: T, token: Any?) {
        sendToTargetOrType(message, null, token)
    }

    /**
     * send message to recipient directly
     *
     * @param message any object can to be a message
     * @param target  send to recipient directly with has not any message,
     * WeakMessenger.getDefault().register(this, ..) in a activity,if target set this activity
     * it will receive the message
     * @param <T>     message data type
     * @param <R>     target
    </R></T> */
    fun <T : Any, R : Any> sendToTarget(message: T, target: R) {
        sendToTargetOrType(message, target.javaClass, null)
    }

    /**
     * Unregister the receiver such as:
     * WeakMessenger.getDefault().unregister(this)" in onDestroy in the Activity is required avoid
     * to
     * memory leak!
     *
     * @param recipient receiver of message
     */
    fun unregister(recipient: Any?) {
        unregisterFromLists(recipient, recipientsOfSubclassesAction)
        unregisterFromLists(recipient, recipientsStrictAction)
        cleanup()
    }

    fun <T> unregister(recipient: Any?, token: Any?) {
        unregisterFromLists(recipient, token, null, recipientsStrictAction)
        unregisterFromLists(recipient, token, null, recipientsOfSubclassesAction)
        cleanup()
    }

    private fun sendToTargetOrType(messageTargetType: Type?, token: Any?) {
        val messageType: Class<*> = NotMsgType::class.java
        if (recipientsOfSubclassesAction != null) { // Clone to protect from people registering in a "receive message" method
// Bug correction Messaging BL0008.002
//            var listClone = recipientsOfSubclassesAction.Keys.Take(_recipientsOfSubclassesAction.Count()).ToList();
            val listClone: MutableList<Type> = ArrayList()
            listClone.addAll(recipientsOfSubclassesAction!!.keys)
            for (type in listClone) {
                var list: List<WeakActionAndToken>? = null
                if (messageType == type || (type as Class<*>).isAssignableFrom(messageType)
                        || classImplements(messageType, type)) {
                    list = recipientsOfSubclassesAction!![type]
                }
                sendToList(list, messageTargetType, token)
            }
        }
        if (recipientsStrictAction != null) {
            if (recipientsStrictAction!!.containsKey(messageType)) {
                val list: List<WeakActionAndToken>? = recipientsStrictAction!![messageType]
                sendToList(list, messageTargetType, token)
            }
        }
        cleanup()
    }

    private fun <T : Any> sendToTargetOrType(message: T, messageTargetType: Type?, token: Any?) {
        val messageType: Class<*> = message.javaClass
        if (recipientsOfSubclassesAction != null) { // Clone to protect from people registering in a "receive message" method
// Bug correction Messaging BL0008.002
//            var listClone = recipientsOfSubclassesAction.Keys.Take(_recipientsOfSubclassesAction.Count()).ToList();
            val listClone: MutableList<Type> = ArrayList()
            listClone.addAll(recipientsOfSubclassesAction!!.keys)
            for (type in listClone) {
                var list: List<WeakActionAndToken>? = null
                if (messageType == type || (type as Class<*>).isAssignableFrom(messageType)
                        || classImplements(messageType, type)) {
                    list = recipientsOfSubclassesAction!![type]
                }
                sendToList(message, list, messageTargetType, token)
            }
        }
        if (recipientsStrictAction != null) {
            if (recipientsStrictAction!!.containsKey(messageType)) {
                val list: List<WeakActionAndToken>? = recipientsStrictAction!![messageType]
                sendToList(message, list, messageTargetType, token)
            }
        }
        cleanup()
    }

    private inner class WeakActionAndToken(private var action: WeakAction<Any>, var token: Any?) {
        fun getAction(): WeakAction<Any> {
            return action
        }

        fun setAction(action: WeakAction<Any>) {
            this.action = action
        }

    }

    class NotMsgType
    companion object {
        private var defaultInstance: Messenger? = null
        @JvmStatic
        val default: Messenger?
            get() {
                if (defaultInstance == null) {
                    defaultInstance = Messenger()
                }
                return defaultInstance
            }

        fun overrideDefault(newWeakMessenger: Messenger?) {
            defaultInstance = newWeakMessenger
        }

        fun reset() {
            defaultInstance = null
        }

        private fun <T : Any> sendToList(
                message: T,
                list: Collection<WeakActionAndToken>?,
                messageTargetType: Type?,
                token: Any?) {
            if (list != null) { // Clone to protect from people registering in a "receive message" method
// Bug correction Messaging BL0004.007
                val listClone = ArrayList<WeakActionAndToken>()
                listClone.addAll(list)
                for (item in listClone) {
                    val executeAction: WeakAction<Any> = item.getAction()
                    if (executeAction != null && item.getAction()!!.isLive
                            && item.getAction()!!.target != null && (messageTargetType == null || item.getAction()!!.target.javaClass == messageTargetType || classImplements(item.getAction()!!.target.javaClass, messageTargetType))
                            && (item.token == null && token == null
                                    || item.token != null && item.token == token)) {
                        executeAction.execute(message)
                    }
                }
            }
        }

        private fun unregisterFromLists(recipient: Any?, lists: HashMap<Type, MutableList<WeakActionAndToken>?>?) {
            if (recipient == null || lists == null || lists.size == 0) {
                return
            }
            synchronized(lists) {
                for (messageType in lists.keys) {
                    for (item in lists[messageType]!!) {
                        val weakAction = item.getAction()
                        if (weakAction != null
                                && recipient === weakAction.target) {
                            weakAction.markForDeletion()
                        }
                    }
                }
            }
            cleanupList(lists)
        }

        private fun <T> unregisterFromLists(
                recipient: Any?,
                action: BindingConsumer<T>?,
                lists: HashMap<Type, List<WeakActionAndToken>>?,
                tClass: Class<T>) {
            val messageType: Type = tClass
            if (recipient == null || lists == null || lists.size == 0 || !lists.containsKey(messageType)) {
                return
            }
            synchronized(lists) {
                for (item in lists[messageType]!!) {
                    val weakActionCasted = item.getAction() as WeakAction<T>?
                    if (weakActionCasted != null && recipient === weakActionCasted.target && (action == null
                                    || action === weakActionCasted.bindingConsumer)) {
                        item.getAction()!!.markForDeletion()
                    }
                }
            }
        }

        private fun unregisterFromLists(
                recipient: Any?,
                action: BindingAction?,
                lists: HashMap<Type, List<WeakActionAndToken>>?
        ) {
            val messageType: Type = NotMsgType::class.java
            if (recipient == null || lists == null || lists.size == 0 || !lists.containsKey(messageType)) {
                return
            }
            synchronized(lists) {
                for (item in lists[messageType]!!) {
                    val weakActionCasted = item.getAction()
                    if (weakActionCasted != null && recipient === weakActionCasted.target && (action == null
                                    || action === weakActionCasted.bindingAction)) {
                        item.getAction()!!.markForDeletion()
                    }
                }
            }
        }

        private fun <T> unregisterFromLists(
                recipient: Any?,
                token: Any?,
                action: BindingConsumer<T>?,
                lists: HashMap<Type, List<WeakActionAndToken>>?, tClass: Class<T>) {
            val messageType: Type = tClass
            if (recipient == null || lists == null || lists.size == 0 || !lists.containsKey(messageType)) {
                return
            }
            synchronized(lists) {
                for (item in lists[messageType]!!) {
                    val weakActionCasted = item.getAction() as WeakAction<T>?
                    if (weakActionCasted != null && recipient === weakActionCasted.target && (action == null
                                    || action === weakActionCasted.bindingConsumer)
                            && (token == null
                                    || token == item.token)) {
                        item.getAction()!!.markForDeletion()
                    }
                }
            }
        }

        private fun unregisterFromLists(
                recipient: Any?,
                token: Any?,
                action: BindingAction?,
                lists: HashMap<Type, MutableList<WeakActionAndToken>?>?) {
            val messageType: Type = NotMsgType::class.java
            if (recipient == null || lists == null || lists.size == 0 || !lists.containsKey(messageType)) {
                return
            }
            synchronized(lists) {
                for (item in lists[messageType]!!) {
                    val weakActionCasted = item.getAction()
                    if (weakActionCasted != null && recipient === weakActionCasted.target && (action == null
                                    || action === weakActionCasted.bindingAction)
                            && (token == null
                                    || token == item.token)) {
                        item.getAction()!!.markForDeletion()
                    }
                }
            }
        }

        private fun classImplements(instanceType: Type?, interfaceType: Type?): Boolean {
            if (interfaceType == null
                    || instanceType == null) {
                return false
            }
            val interfaces = (instanceType as Class<*>).interfaces
            for (currentInterface in interfaces) {
                if (currentInterface == interfaceType) {
                    return true
                }
            }
            return false
        }

        private fun cleanupList(lists: HashMap<Type, MutableList<WeakActionAndToken>?>?) {
            if (lists == null) {
                return
            }
            val it: Iterator<*> = lists.entries.iterator()
            while (it.hasNext()) {
                val key = it.next()!!
                val itemList = lists[key]
                if (itemList != null) {
                    for (item in itemList) {
                        if (item.getAction() == null
                                || !item.getAction()!!.isLive) {
                            itemList.remove(item)
                        }
                    }
                    if (itemList.size == 0) {
                        lists.remove(key)
                    }
                }
            }
        }

        private fun sendToList(
                list: Collection<WeakActionAndToken>?,
                messageTargetType: Type?,
                token: Any?) {
            if (list != null) { // Clone to protect from people registering in a "receive message" method
// Bug correction Messaging BL0004.007
                val listClone = ArrayList<WeakActionAndToken>()
                listClone.addAll(list)
                for (item in listClone) {
                    val executeAction = item.getAction()
                    if (executeAction != null && item.getAction()!!.isLive
                            && item.getAction()!!.target != null && (messageTargetType == null || item.getAction()!!.target.javaClass == messageTargetType || classImplements(item.getAction()!!.target.javaClass, messageTargetType))
                            && (item.token == null && token == null
                                    || item.token != null && item.token == token)) {
                        executeAction.execute()
                    }
                }
            }
        }
    }
}