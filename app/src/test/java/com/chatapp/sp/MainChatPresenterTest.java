package com.chatapp.sp;

import com.chatapp.sp.module.MessageItem;
import com.chatapp.sp.presenter.MainChatPresenter;
import com.chatapp.sp.repository.ChatAppRepository;
import com.chatapp.sp.screen.MainChatMvpView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        MessageItem messageItem = new MessageItem(System.currentTimeMillis(), Constant.TYPE_INCOMING_MESSAGE, false,
            "test");

        when(mockRepository.isConnected()).thenReturn(true);
        presenter.sendMessage("test", messageItem);
        verify(mockMvpView).showOutgoingMessage(true);
    }

    @Test
    public void verifyEmptyOutgoingMessage() throws Exception {
        MessageItem messageItem = new MessageItem(System.currentTimeMillis() - 600010, Constant
            .TYPE_INCOMING_MESSAGE, false,
            "test");

        when(mockRepository.isConnected()).thenReturn(true);
        presenter.sendMessage("", messageItem);
        verify(mockMvpView, never()).showTimestamp();
        verify(mockMvpView, never()).showOutgoingMessage(true);
    }

    @Test
    public void verifyMessageNotSendingWhenDisconnected() throws Exception {
        MessageItem messageItem = new MessageItem(System.currentTimeMillis() - 600010, Constant
            .TYPE_INCOMING_MESSAGE, false,
            "test");

        when(mockRepository.isConnected()).thenReturn(false);
        presenter.sendMessage("test", messageItem);
        verify(mockMvpView, never()).showTimestamp();
        verify(mockMvpView, never()).showOutgoingMessage(true);
        verify(mockMvpView).showOnConnectError();
    }

    @Test
    public void verifyTimestamp() throws Exception {
        MessageItem messageItem = new MessageItem(System.currentTimeMillis() - 600010, Constant
            .TYPE_INCOMING_MESSAGE, false,
            "test");

        when(mockRepository.isConnected()).thenReturn(true);
        presenter.sendMessage("test", messageItem);
        verify(mockMvpView).showTimestamp();
        verify(mockMvpView).showOutgoingMessage(true);
    }

    @Test
    public void verifNoTimestamp() throws Exception {
        MessageItem messageItem = new MessageItem(System.currentTimeMillis(), Constant
            .TYPE_INCOMING_MESSAGE, false,
            "test");

        when(mockRepository.isConnected()).thenReturn(true);
        presenter.sendMessage("test", messageItem);
        verify(mockMvpView, never()).showTimestamp();
        verify(mockMvpView).showOutgoingMessage(true);
    }

    @After
    public void tearDown() {
        presenter.onDetach();
    }
}