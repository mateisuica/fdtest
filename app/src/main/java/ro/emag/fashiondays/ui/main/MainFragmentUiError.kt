package ro.emag.fashiondays.ui.main

sealed class MainFragmentUiError {
    object NoProducts : MainFragmentUiError()
    object GenericError : MainFragmentUiError()
}