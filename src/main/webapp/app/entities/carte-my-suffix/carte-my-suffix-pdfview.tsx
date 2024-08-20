import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { pdfjs } from 'react-pdf';
const PdfViewer: React.FC<{ id: string }> = ({ id }) => {
  const [pdfUrl, setPdfUrl] = useState<string | null>(null);
  const apiUrlPdf = '/api/cartes/pdf';
  const url1 = 'http://localhost:8080/api/cartes/pdf';

  pdfjs.GlobalWorkerOptions.workerSrc = new URL('pdfjs-dist/build/pdf.worker.min.js', import.meta.url).toString();

  useEffect(() => {
    const fetchPdf = async () => {
      const requestUrl = `${apiUrlPdf}/${id}`;
      try {
        const response = await axios.get(requestUrl, {
          responseType: 'blob',
        });
        const pdfBlob = new Blob([response.data], { type: 'application/pdf' });
        setPdfUrl(URL.createObjectURL(pdfBlob));
      } catch (error) {
        setPdfUrl(null);
      }
    };

    fetchPdf().then(r => console.log('hello' + r));
  }, [id]);

  return <div>{pdfUrl && <iframe src={`${pdfUrl}`} width="100%" height="600px" />}</div>;
};

export default PdfViewer;
