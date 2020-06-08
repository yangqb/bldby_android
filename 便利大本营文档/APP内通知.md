# APP内通知

```java
//发送通知
RxBus.getDefault().post(RxCodeConstants.NETWORKSTATUS, networkStatus);
//接收通知
RxBus.getDefault().toObservable(IM_MESSAGE, Boolean.class).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                refrshMsg();
            }
        });
```

