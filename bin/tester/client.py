import socket

while True:
    sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    sock.connect(("127.0.0.1",4444))


    sock.send(b"data")
    print(sock.recv(1024))