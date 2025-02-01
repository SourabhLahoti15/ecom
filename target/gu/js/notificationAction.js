function markAsRead(notificationId){
    const url = "/gu/markAsRead?notificationId=" + notificationId;
    fetch(url,{
        method: "POST",
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            localStorage.setItem('notification', JSON.stringify({
                success: data.success,
                message: data.message,
                type: data.type
            }));
            window.location.reload();
        } else {
            showNotification(data.success, data.message, data.type);
        }
    })
    .catch(error => {
        // console.error('Error: ', error);
        showNotification('false', 'Error occured while marking as read', 'error');
    });
}
function deleteNotification(notificationId){
    const url = "/gu/deleteNotification?notificationId=" + notificationId;
    fetch(url,{
        method: "POST",
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            localStorage.setItem('notification', JSON.stringify({
                success: data.success,
                message: data.message,
                type: data.type
            }));
            window.location.reload();
        } else {
            showNotification(data.success, data.message, data.type);
        }
    })
    .catch(error => {
        // console.error('Error: ', error);
        showNotification('false', 'Error occured while marking as read', 'error');
    });
}
function clearallNotifications(){
    const url = "/gu/clearallNotifications";
    fetch(url,{
        method: "POST",
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            localStorage.setItem('notification', JSON.stringify({
                success: data.success,
                message: data.message,
                type: data.type
            }));
            window.location.reload();
        } else {
            showNotification(data.success, data.message, data.type);
        }
    })
    .catch(error => {
        // console.error('Error: ', error);
        showNotification('false', 'Error occured while marking as read', 'error');
    });
}
document.addEventListener('DOMContentLoaded', function() {
    const notificationData = localStorage.getItem('notification');
    if (notificationData) {
        const {success, message, type} = JSON.parse(notificationData);
        showNotification(success, message, type);
        localStorage.removeItem('notification');
    }
})