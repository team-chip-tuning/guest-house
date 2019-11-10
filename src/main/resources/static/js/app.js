function showToast() {
    var title = document.getElementById('param-toast-title').getAttribute('data-toast-title');
    var content = document.getElementById('param-toast-content').getAttribute('data-toast-content');
    var msg = document.getElementById('param-toast-msg').getAttribute('data-toast-msg') || 'info';
    if (title && content) {
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-bottom-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "400",
            "timeOut": "1000",
            "extendedTimeOut": "500",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        if (msg == 'success') {
            toastr['success'](content, title);
        }
        if (msg == 'info') {
            toastr['info'](content, title);
        }
        if (msg == 'error') {
            toastr['error'](content, title);
        }
    }
}