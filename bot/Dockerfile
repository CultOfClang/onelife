FROM python:3
RUN git clone https://github.com/ammaraskar/pyCraft
RUN pip install --no-cache-dir -e ./pyCraft
COPY . .
CMD [ "python", "./start.py" ]
