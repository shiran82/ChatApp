package com.chatapp.sp;

import com.chatapp.sp.presenter.MainChatPresenter;
import com.chatapp.sp.repository.ChatAppRepository;
import com.chatapp.sp.screen.MainChatMvpView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainChatPresenterTest {
    private MainChatPresenter presenter;
    @Mock
    MainChatMvpView mockMvpView;
    @Mock
    ChatAppRepository mockRepository;

    @Before
    public void setUp() {
        presenter = new MainChatPresenter(mockRepository, mockMvpView);
    }

    @Test
    public void verifyOutgoingMessage() throws Exception {
        when(mockRepository.isConnected()).thenReturn(true);
        presenter.sendMessage("test");
        verify(mockMvpView).showOutgoingMessage(anyBoolean(), anyLong());
    }

    @Test
    public void verifyEmptyOutgoingMessage() throws Exception {
        presenter.sendMessage("");
        verify(mockMvpView, never()).showTimestamp();
        verify(mockMvpView, never()).showOutgoingMessage(true, System.currentTimeMillis());
    }

    @Test
    public void verifyMessageNotSendingWhenDisconnected() throws Exception {
        when(mockRepository.isConnected()).thenReturn(false);
        presenter.sendMessage("test");
        verify(mockMvpView, never()).showTimestamp();
        verify(mockMvpView, never()).showOutgoingMessage(true, System.currentTimeMillis());
        verify(mockMvpView).showOnConnectError();
    }

    @Test
    public void verifyTimestamp() throws Exception {

        when(mockRepository.isConnected()).thenReturn(true);
        presenter.sendMessage("test");
        verify(mockMvpView).showTimestamp();
        verify(mockMvpView).showOutgoingMessage(anyBoolean(), anyLong());
    }

    @Test
    public void verifyNoTimestamp() throws Exception {
        long time = Constant.INTERVAL_TO_SHOW_TIMESTAMP - 1;

        when(mockRepository.isConnected()).thenReturn(true);
        when(mockMvpView.getTimeBetweenLastAndNewItem(anyLong())).thenReturn(time);
        presenter.sendMessage("test");
        verify(mockMvpView, never()).showTimestamp();
        verify(mockMvpView).showOutgoingMessage(anyBoolean(), anyLong());
    }

    @After
    public void tearDown() {
        presenter.onDetach();
    }
}