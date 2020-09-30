package com.whdx.base.util.binding.command

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/25 0025 12:58
 */

interface BindingAction {
    fun call()
}

interface BindingConsumer<T> {
    fun call(t: T)
}

interface BindingFunction<T> {
    fun call(): T
}

class BindingCommand<T> {
    private var execute: BindingAction? = null;
    private var consumer: BindingConsumer<T>? = null;
    private var canExecute: BindingFunction<Boolean>? = null;

    constructor(execute: BindingAction) {
        this.execute = execute
    }

    constructor(consumer: BindingConsumer<T>) {
        this.consumer = consumer
    }

    constructor(execute: BindingAction, canExecute: BindingFunction<Boolean>) {
        this.execute = execute
        this.canExecute = canExecute
    }

    constructor(consumer: BindingConsumer<T>, canExecute: BindingFunction<Boolean>) {
        this.consumer = consumer
        this.canExecute = canExecute
    }

    fun execute() {
        if (execute != null && canExecute()) {
            execute?.call()
        }
    }

    fun execute(t: T) {
        if (consumer != null && canExecute()) {
            consumer?.call(t)
        }
    }


    private fun canExecute(): Boolean {
        if (canExecute == null) {
            return true
        } else {
            return canExecute!!.call()
        }
    }
}